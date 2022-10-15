package features.task_editor.presentation.entities

import androidx.compose.runtime.Immutable
import com.lauruscorp.sexify_data.entities.SexifySex

@Immutable
data class SexesSelectorUiData(
    val sexes: List<SexifySex>,
    val selectedSexes: List<SexifySex>,
    val error: String? = null
)