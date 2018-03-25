package papered.startupweekend.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.android.synthetic.main.activity_show_price.*
import papered.startupweekend.R

class ShowPriceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_price)

        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(4f, 0))
        entries.add(BarEntry(6f, 1))
        val label = ArrayList<String>()
        label.add("이용 가격")
        label.add("타 서비스 가격")
        val dataSet = BarDataSet(entries, "가격")
        val data = BarData(label, dataSet)
        showPrice_barChart.data = data

        showPrice_button_nextStep.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
            finish()
        }

    }
}
