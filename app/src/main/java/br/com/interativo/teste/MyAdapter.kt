package br.com.interativo.teste

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

@Suppress("DEPRECATION")
internal class MyAdapter(
    var context: Context,
    fm: FragmentManager,
    var totalTabs: Int
) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                Shonen()
            }
            1 -> {
                Shoujo()
            }

            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}