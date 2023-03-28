package fes.aragon.app.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import fes.aragon.app.view.adapter.TopAdapter
import fes.aragon.app.databinding.ActivityMainBinding
import fes.aragon.app.view.adapter.BottomAdapter
import fes.aragon.app.viewmodel.DataViewModel

class MainActivity : AppCompatActivity(), TopAdapter.OnDogClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterListTop: TopAdapter
    private lateinit var recyclerViewTop: RecyclerView
    private lateinit var adapterListBottom: BottomAdapter
    private lateinit var recyclerViewBottom: RecyclerView
    private val dataViewModel: DataViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        refresh()
        dataViewModel.listTop.observe(this) {
            adapterListTop = TopAdapter(it,this)
            recyclerViewTop = binding.listView1
            recyclerViewTop.adapter = adapterListTop
        }
        dataViewModel.listBottom.observe(this) {
            adapterListBottom = BottomAdapter(it)
            recyclerViewBottom = binding.listView2
            recyclerViewBottom.adapter = adapterListBottom
        }
    }

    override fun onDogClick(position: Int) {
        dataViewModel.deleteItemListTop(position)
        refresh()
    }

    private fun refresh() {
        dataViewModel.getListTop()
        dataViewModel.getListBottom()
    }

}