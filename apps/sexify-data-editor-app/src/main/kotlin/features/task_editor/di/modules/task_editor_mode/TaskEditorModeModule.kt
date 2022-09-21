package features.task_editor.di.modules.task_editor_mode

import dagger.Module
import dagger.Provides
import features.task_editor.di.component.qualifiers.TaskEditorTaskIdQualifier
import features.task_editor.di.modules.task_editor_mode.qualifiers.TaskEditorModeQualifier
import features.task_editor.domain.entities.TaskEditorMode

@Module
internal class TaskEditorModeModule {
	@Provides
	@TaskEditorModeQualifier
	fun provideTaskEditorMode(
		@TaskEditorTaskIdQualifier taskId: Long?
	): TaskEditorMode {
		return if (taskId == null) {
			TaskEditorMode.Create
		} else {
			TaskEditorMode.Edit
		}
	}
}