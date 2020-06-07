package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.fragments.ClassFragment
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.fragments.NotesFragment
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.fragments.GraphFragment

class UpperPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object{
        private const val COUNT = 3
        const val CLASSES = 0
        const val NOTES = 1
        const val GRAF = 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            CLASSES -> ClassFragment()
            NOTES -> NotesFragment()
            else -> GraphFragment()
        }
    }

    override fun getCount(): Int {
        return COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            CLASSES -> "Raspored"
            NOTES -> "Beleske"
            else -> "Ulancani graf"
        }
    }
}