package com.pocket.allinone.ui

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.pocket.allinone.R
import com.pocket.allinone.adapter.ToolsAdapter
import com.pocket.allinone.models.Tool
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getLayoutResource(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = resources.getString(R.string.app_name)
        setHomeButtonEnabled(false)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val layoutManager = GridLayoutManager(this, 3)
        rv_tools.layoutManager = layoutManager
        val adapter = ToolsAdapter(this, getToolList(), onItemClickListener)
        rv_tools.adapter = adapter
    }

    private fun getToolList(): ArrayList<Tool> {
        val toolList = ArrayList<Tool>()
        toolList.add(
            Tool(
                resources.getString(R.string.qr_and_barcode_scanner),
                R.drawable.ic_qr_code
            )
        )
        toolList.add(
            Tool(
                resources.getString(R.string.unknown_whatsapp_number),
                R.drawable.ic_whatsapp
            )
        )
        toolList.add(
            Tool(
                resources.getString(R.string.random_text_generator),
                R.drawable.ic_text_editor
            )
        )
        toolList.add(Tool(resources.getString(R.string.torch), R.drawable.ic_torch))
        toolList.add(Tool(resources.getString(R.string.compass), R.drawable.ic_compass))
        toolList.add(Tool(resources.getString(R.string.whatsapp_cleanup), R.drawable.ic_cleanup))

        return toolList
    }

    private val onItemClickListener = object : ToolsAdapter.OnItemClickListener {
        override fun onItemClick(toolName: String) {
            lateinit var intent: Intent
            when (toolName) {
                resources.getString(R.string.qr_and_barcode_scanner) -> {
                    intent = Intent(this@MainActivity, QrCodeActivity::class.java)
                }
                resources.getString(R.string.unknown_whatsapp_number) -> {
                    intent = Intent(this@MainActivity, UnknownNumberActivity::class.java)
                }
                resources.getString(R.string.random_text_generator) -> {
                    intent = Intent(this@MainActivity, UnknownNumberActivity::class.java)
                }
                resources.getString(R.string.torch) -> {
                    intent = Intent(this@MainActivity, TorchActivity::class.java)
                }
                resources.getString(R.string.whatsapp_cleanup) -> {
                    intent = Intent(this@MainActivity, UnknownNumberActivity::class.java)
                }
                resources.getString(R.string.compass) -> {
                    intent = Intent(this@MainActivity, CompassActivity::class.java)
                }
                else -> {
                    print("Unknown Name")
                }
            }
            startActivity(intent)
        }

    }
}