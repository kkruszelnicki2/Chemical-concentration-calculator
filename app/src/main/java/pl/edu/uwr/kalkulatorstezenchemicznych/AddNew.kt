package pl.edu.uwr.kalkulatorstezenchemicznych

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

object addCompounds {
    val compounds: MutableList<Compound> = mutableListOf(
        Compound("Tlenek baru","BaO",153.33),
        Compound("Tlenek glinu","AlO",101.96),
        Compound("Tlenek cyny","SnO",134.71),
        Compound("Tlenek ołowiu","PbO",223.2),
        Compound("Tlenek cynku","ZnO",81.38),
    )
}

class AddNew : Fragment() {

    private var mContext: Context? = null

    private val compoundsList: MutableList<Compound> = addCompounds.compounds

    lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_new, container, false)

        recyclerView = view.findViewById(R.id.compoundRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = AddCompoundAdapter(requireContext(), compoundsList)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getTasksList(requireContext())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    fun getTasksList(context: Context) {
        (recyclerView.adapter as AddCompoundAdapter).getCompoundList(context)
    }
}