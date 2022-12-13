import android.R
import android.app.Dialog
import android.content.Context
import android.view.Window
import closet.databinding.DialogProgressBinding

class ProgressDialog(context: Context?) : Dialog(context!!) {

    private val binding by lazy {
        DialogProgressBinding.inflate(layoutInflater)
    }
    init {
        // 다이얼 로그 제목을 안보이게...
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)
    }
}