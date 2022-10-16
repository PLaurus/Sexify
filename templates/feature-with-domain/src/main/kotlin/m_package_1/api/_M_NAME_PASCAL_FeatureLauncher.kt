package m_package_1.api

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import com.lauruscorp.core.android.application.SimpleActivityLifecycleCallbacks
import m_package_1.di.component._M_NAME_PASCAL_FeatureComponent
import m_package_1.di.component._M_NAME_PASCAL_FeatureComponentsRegistry
import m_package_1.presentation._M_NAME_PASCAL_Activity

class _M_NAME_PASCAL_FeatureLauncher(
    private val dependencies: _M_NAME_PASCAL_FeatureDependencies
) {
    fun start(
        application: Application,
        context: Context,
        onButtonClick: ((context: Context) -> Unit) = {},
    ) {
        val example2FeatureComponent = _M_NAME_PASCAL_FeatureComponentsRegistry.createAndRegister {
            _M_NAME_PASCAL_FeatureComponent(
                featureId = System.nanoTime(),
                dependencies = dependencies,
                onButtonClick = onButtonClick
            )
        }

        val intent = Intent(context, _M_NAME_PASCAL_Activity::class.java).apply {
            val featureId = example2FeatureComponent.getFeatureId()
            putExtra(FEATURE_ID_KEY, featureId)
        }

        application.registerActivityLifecycleCallbacks(object : SimpleActivityLifecycleCallbacks {
            override fun onActivityDestroyed(activity: Activity) {
                super.onActivityDestroyed(activity)

                if (activity is _M_NAME_PASCAL_Activity && activity.isFinishing) {
                    _M_NAME_PASCAL_FeatureComponentsRegistry.unregisterComponent(
                        example2FeatureComponent
                    )
                    application.unregisterActivityLifecycleCallbacks(this)
                }
            }
        })

        context.startActivity(intent)
    }

    companion object {
        internal const val FEATURE_ID_KEY = "FEATURE_ID_KEY"
    }
}