package mendoza.omar.guessthenumber

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Random

class MainActivity : AppCompatActivity() {

    var minValue = 0
    var maxValue = 100
    var num: Int = 0
    var won = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val guessings: TextView = findViewById(R.id.guessings)
        val down: Button = findViewById(R.id.down)
        val up: Button = findViewById(R.id.up)
        val generate: Button = findViewById(R.id.generate)
        val guessed: Button = findViewById(R.id.guessed)

        generate.setOnClickListener {
            num = kotlin.random.Random.nextInt(minValue, maxValue)
            guessings.setText(num.toString())
            generate.visibility = View.INVISIBLE
            guessed.visibility = View.VISIBLE
        }

        up.setOnClickListener{
            minValue = num
            if (checkingLimits()) {
                num = kotlin.random.Random.nextInt(minValue, maxValue)
                guessings.setText(num.toString())
            }else{
                guessings.setText("No puede ser")
            }
        }

        down.setOnClickListener{
            maxValue = num
            if (checkingLimits()){
            num = kotlin.random.Random.nextInt(minValue, maxValue)
            guessings.setText(num.toString())
            }else{
                guessings.setText("No puede ser")
            }
        }

        guessed.setOnClickListener(){
            if(!won) {
                guessings.setText("Adiviné, tu número es el" + num)
                guessed.setText("Volver a jugar")
                won = true
            } else {
                generate.visibility = View.VISIBLE
                guessed.setText("Tap on generate to start")
                guessed.visibility = View.GONE
                resetValues()
            }
        }



    }

    fun resetValues(){
        minValue = 0
        maxValue = 100
        num = 0
        won = false
    }

    fun checkingLimits(): Boolean{
        return minValue != maxValue



    }
}