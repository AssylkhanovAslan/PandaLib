package kz.pandamobile

import android.os.Bundle
import kz.pandamobile.pandalib.PandaActivity

class MainActivity : PandaActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showToast("Check")
    }
}
