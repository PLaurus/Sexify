package _M_PACKAGE_1_.api

import _M_PACKAGE_1_.di.component._M_NAME_PASCAL_FeatureComponent
import _M_PACKAGE_1_.di.component._M_NAME_PASCAL_FeatureComponentsRegistry
import _M_PACKAGE_1_.presentation._M_NAME_PASCAL_Activity
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import com.lauruscorp.core.android.application.SimpleActivityLifecycleCallbacks

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
			putExtra(_M_NAME_PASCAL_Activity.FEATURE_ID_KEY, featureId)
		}
		
		application.registerActivityLifecycleCallbacks(object : SimpleActivityLifecycleCallbacks {
			override fun onActivityDestroyed(activity: Activity) {
				super.onActivityDestroyed(activity)
				
				if (activity is _M_NAME_PASCAL_Activity && activity.isFinishing) {
					_M_NAME_PASCAL_FeatureComponentsRegistry.unregisterComponent(example2FeatureComponent)
					application.unregisterActivityLifecycleCallbacks(this)
				}
			}
		})
		
		context.startActivity(intent)
	}
}