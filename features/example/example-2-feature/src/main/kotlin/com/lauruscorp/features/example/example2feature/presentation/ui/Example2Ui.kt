package com.lauruscorp.features.example.example2feature.presentation.ui

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.lauruscorp.core_android.ui.ViewBindingUi
import com.lauruscorp.features.example.example2feature.R
import com.lauruscorp.features.example.example2feature.databinding.LayoutExample2Binding
import com.lauruscorp.features.example.example2feature.presentation.entities.PresentationOperation
import com.lauruscorp.features.example.example2feature.presentation.entities.UiError
import com.lauruscorp.features.example.example2feature.presentation.viewmodel.Example2ViewModel
import javax.inject.Inject

internal class Example2Ui @Inject constructor(
    viewBindingProvider: @JvmSuppressWildcards (view: View) -> LayoutExample2Binding,
    private val viewModel: Example2ViewModel,
    private val onButtonClick: (context: Context) -> Unit
) : ViewBindingUi<LayoutExample2Binding>(viewBindingProvider) {

    override fun onBound(
        viewBinding: LayoutExample2Binding,
        lifecycleOwner: LifecycleOwner
    ) {
        super.onBound(viewBinding, lifecycleOwner)
        viewBinding.run {
            initializeAFieldText(lifecycleOwner)
            initializeOperationButtonsGroup(lifecycleOwner)
            initializeBFieldText(lifecycleOwner)
            initializeResultText(lifecycleOwner)
            initializeStartAnotherFeatureButton()
            initializeToast(lifecycleOwner)
        }
    }

    private fun LayoutExample2Binding.initializeAFieldText(lifecycleOwner: LifecycleOwner) {
        aFieldText.apply {
            viewModel.a.observe(lifecycleOwner) { a ->
                if (text?.toString() != a) {
                    setText(a)
                    setSelection(a.length)
                }
            }

            addTextChangedListener {
                viewModel.updateA(it?.toString() ?: "")
            }
        }
    }

    private fun LayoutExample2Binding.initializeOperationButtonsGroup(lifecycleOwner: LifecycleOwner) {
        viewModel.operation.observe(lifecycleOwner, Observer { operation ->
            val operationButtonId = when (operation) {
                PresentationOperation.Plus -> R.id.additionButton
                PresentationOperation.Minus -> R.id.subtractionButton
                PresentationOperation.Multiply -> R.id.multiplicationButton
                PresentationOperation.Divide -> R.id.divisionButton
                else -> View.NO_ID
            }

            if (operationButtonId == View.NO_ID) {
                if (operationButtonsGroup.checkedButtonIds.isEmpty()) {
                    return@Observer
                }

                operationButtonsGroup.clearChecked()
            } else {
                if (operationButtonsGroup.checkedButtonId == operationButtonId) {
                    return@Observer
                }

                operationButtonsGroup.check(operationButtonId)
            }
        })

        operationButtonsGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            val operation = if (isChecked) {
                when (checkedId) {
                    R.id.additionButton -> PresentationOperation.Plus
                    R.id.subtractionButton -> PresentationOperation.Minus
                    R.id.multiplicationButton -> PresentationOperation.Multiply
                    R.id.divisionButton -> PresentationOperation.Divide
                    else -> PresentationOperation.None
                }
            } else if (group.checkedButtonIds.isEmpty()) {
                PresentationOperation.None
            } else null

            operation?.let(viewModel::updateSelectedOperation)
        }
    }

    private fun LayoutExample2Binding.initializeBFieldText(lifecycleOwner: LifecycleOwner) {
        bFieldText.apply {
            viewModel.b.observe(lifecycleOwner) { b ->
                if (text?.toString() != b) {
                    bFieldText.setText(b)
                    setSelection(b.length)
                }
            }

            doAfterTextChanged {
                viewModel.updateB(it?.toString() ?: "")
            }
        }
    }

    private fun LayoutExample2Binding.initializeResultText(lifecycleOwner: LifecycleOwner) {
        resultText.apply {
            viewModel.result.observe(lifecycleOwner) { result ->
                if (text?.toString() != result) {
                    text = result
                }
            }
        }
    }

    private fun LayoutExample2Binding.initializeStartAnotherFeatureButton() {
        startAnotherFeatureButton.setOnClickListener {
            onButtonClick(it.context)
        }
    }

    private fun LayoutExample2Binding.initializeToast(lifecycleOwner: LifecycleOwner) {
        viewModel.errorEvent.observe(lifecycleOwner) { uiError ->
            val context = root.context
            val message = when (uiError) {
                is UiError.System -> uiError.throwable.localizedMessage
                UiError.NoA -> context.getString(R.string.a_value_is_not_provided)
                UiError.NoB -> context.getString(R.string.b_value_is_not_provided)
                UiError.NoOperation -> context.getString(R.string.operation_value_is_not_provided)
            }

            Toast.makeText(context, message, Toast.LENGTH_SHORT)
                .show()
        }
    }
}