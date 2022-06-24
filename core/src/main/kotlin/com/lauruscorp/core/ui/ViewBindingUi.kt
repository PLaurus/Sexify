package com.lauruscorp.core.ui

import android.view.View
import androidx.annotation.CallSuper
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding

abstract class ViewBindingUi<ViewBindingT : ViewBinding>(
	private val viewBindingProvider: (view: View) -> ViewBindingT
) : DefaultLifecycleObserver {
	private var view: View? = null
	private var lifecycle: Lifecycle? = null
	
	protected var viewBinding: ViewBindingT? = null
		private set
	
	@Synchronized
	fun bindToViewLifecycleOwner(
		view: View,
		lifecycle: Lifecycle
	) {
		this.lifecycle?.removeObserver(this)
		
		this.view = view
		this.lifecycle = lifecycle
		
		lifecycle.addObserver(this)
	}
	
	@CallSuper
	protected open fun onBound(viewBinding: ViewBindingT, lifecycleOwner: LifecycleOwner) = Unit
	
	private fun unbindFromViewLifecycleOwner() {
		lifecycle?.removeObserver(this)
		lifecycle = null
		unbindView()
		view = null
	}
	
	override fun onCreate(owner: LifecycleOwner) {
		super.onCreate(owner)
		
		if (!isViewBound()) {
			bindView(
				view = view ?: return,
				lifecycleOwner = owner
			)
		}
	}
	
	override fun onDestroy(owner: LifecycleOwner) {
		unbindFromViewLifecycleOwner()
		super.onDestroy(owner)
	}
	
	private fun bindView(
		view: View,
		lifecycleOwner: LifecycleOwner
	) {
		val viewBinding = viewBindingProvider(view)
		this.viewBinding = viewBinding
		
		onBound(
			viewBinding = viewBinding,
			lifecycleOwner = lifecycleOwner
		)
	}
	
	private fun unbindView() {
		viewBinding = null
	}
	
	private fun isViewBound(): Boolean {
		return viewBinding != null
	}
	
	private fun isBoundToViewLifecycleOwner(): Boolean {
		return view != null && lifecycle != null
	}
}