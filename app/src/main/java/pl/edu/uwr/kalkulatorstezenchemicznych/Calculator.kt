package pl.edu.uwr.kalkulatorstezenchemicznych

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import java.math.RoundingMode
import java.text.DecimalFormat

class Calculator: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.calculator, container, false)

        val name = arguments?.getString("name")
        val moleMass = arguments?.getDouble("moleMass")

        val textName = view.findViewById<TextView>(R.id.compoundName)

        view.findViewById<Button>(R.id.goBack).setOnClickListener {
            val action = CalculatorDirections.toHomeFragment()
            Navigation.findNavController(view).navigate(action)
        }

        view.findViewById<Button>(R.id.count).setOnClickListener {
            val rMass = view.findViewById<EditText>(R.id.solutionMass).text.toString()
            val sMass = view.findViewById<EditText>(R.id.substanceMass).text.toString()
            val initConc = view.findViewById<EditText>(R.id.initialConcentration).text.toString()

            val rV = view.findViewById<EditText>(R.id.solventCap).text.toString()
            val sV = view.findViewById<EditText>(R.id.substanceCap).text.toString()
            val rMoleMass = view.findViewById<EditText>(R.id.moleMass).text.toString()

            val percentageMess = view.findViewById<TextView>(R.id.percentageMess)
            val molarMess = view.findViewById<TextView>(R.id.molarMess)

            val percentageConc = view.findViewById<TextView>(R.id.percentageConc)
            val moleConc = view.findViewById<TextView>(R.id.molarConc)

            val df = DecimalFormat("#.###")
            df.roundingMode = RoundingMode.UP

            if(rMass!="" && sMass!="" && initConc!="" && rMass.toDouble() > 0 && initConc.toDouble() >= 0 && initConc.toDouble() <= 100) {
                percentageMess.text=""

                var pConcValue = df.format(sMass.toDouble()/rMass.toDouble() * (initConc.toDouble()*0.01))
                percentageConc.text = pConcValue.toString()
            }
            else if(rMass=="" || sMass=="") {
                percentageMess.text = "Masa substancji oraz masa roztworu muszą być wypełnione!"
            }
            else if(rMass.toDouble()<=0) {
                percentageMess.text = "Masa roztworu musi być większa niż 0!"
            }
            else if(initConc.toDouble() < 0 || initConc.toDouble() > 100) {
                percentageMess.text = "Stężenie początkowe musi być podane w % (wartość pomiędzy 0 a 100)!"
            }

            if(sMass!="" && rV!="" && sV!="" && rMoleMass!="") {
                molarMess.text=""

                val MMass = rMoleMass.toDouble() + moleMass!!
                val n = sMass.toDouble()/MMass.toDouble()

                val CMol = df.format(n/(rV.toDouble()+sV.toDouble()))
                moleConc.text = CMol.toString()
            }
            else {
                molarMess.text = "Masa substancji, objętości oraz masa molowa rozpuszczalnika nie może być pusta!"
            }
        }

        textName.text = name

        return view
    }
}