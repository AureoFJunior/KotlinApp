package br.com.interativo.teste

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import br.com.interativo.teste.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //setSupportActionBar(binding.toolbar)
        val context = this

        setCadastro(context)
        getLogin(context)
    }

    fun setCadastro(context: Context) {
        binding.buttonSubtract.setOnClickListener {
            if (txtLog.text.toString().length > 0 &&
                txtPassw.text.toString().length > 0
            ) {

                var user = userClass(txtLog.text.toString(), txtPassw.text.toString())
                var db = dataBaseHandler(context)

                var decide = db.getData(user.Username)
                if (decide == "Usuario nao encontrado") {
                    db.insertData(user)
                    Toast.makeText(context, "Cadastrado com Sucesso", Toast.LENGTH_SHORT).show()
                }


            } else {
                Toast.makeText(context, "Por favor, preencha os campos", Toast.LENGTH_SHORT).show()
            }


        }

        binding.labelLink.setOnClickListener {
            val url = "https://github.com/AureoFJunior"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }

    fun getLogin(context: Context) {
        binding.buttonLog.setOnClickListener {
            if (txtLog.text.toString().length > 0 &&
                txtPassw.text.toString().length > 0
            ) {

                var user = userClass(txtLog.text.toString(), txtPassw.text.toString())
                var db = dataBaseHandler(context)

                var decide = db.getData(user.Username)

                when (decide != "Usuario nao encontrado") {
                    true -> {
                        Toast.makeText(context, "Logado com Sucesso", Toast.LENGTH_SHORT).show()
                        openWin(user)
                    }

                    false -> Toast.makeText(context, "Usuário não cadastrado!", Toast.LENGTH_SHORT).show()


                }

            }

        }


    }

    fun openWin(user : userClass) {

        val aux = Intent(this@MainActivity, SecondActivity::class.java)
        val ex = Bundle().apply {
            putParcelable("usuario", user)
        }
        this@MainActivity.startActivity(aux, ex)

    }

}





