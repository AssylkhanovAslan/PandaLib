package kz.pandamobile

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import kz.pandamobile.pandalib.PandaActivity

class MainActivity : PandaActivity() {

    lateinit var lifecycleOwner: LifecycleOwner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showToast("Check")
        lifecycleOwner = this
    }
}
