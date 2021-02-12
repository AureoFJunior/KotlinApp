package br.com.interativo.teste

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import br.com.interativo.teste.databinding.ActivityMainBinding
import com.github.florent37.runtimepermission.RuntimePermission.askPermission
import com.github.florent37.runtimepermission.kotlin.askPermission
import com.mikepenz.iconics.IconicsColor
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome
import com.mikepenz.iconics.typeface.library.materialdesigniconic.MaterialDesignIconic
import com.mikepenz.iconics.utils.color
import com.mikepenz.iconics.utils.paddingDp
import com.mikepenz.iconics.utils.sizeDp
import com.mikepenz.materialdrawer.holder.StringHolder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.withIcon
import com.mikepenz.materialdrawer.model.interfaces.withIdentifier
import com.mikepenz.materialdrawer.model.interfaces.withName
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        askPermission(){
            Toast.makeText(this@SecondActivity, "SAFE!", Toast.LENGTH_SHORT).show()
        }.onDeclined { e ->
            Toast.makeText(this@SecondActivity, "ACEITE!", Toast.LENGTH_SHORT).show()

            this@SecondActivity.finish()
        }


        var usuario = intent.extras?.getParcelable<userClass>("usuario")!!




        setContentView(R.layout.activity_second)
        funteste(usuario)

        fab_next_information.setOnClickListener(){
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, 1)
        }


    }



    fun funteste(user: userClass){

        var db = dataBaseHandler(this@SecondActivity)

        val icon = IconicsDrawable(this@SecondActivity, FontAwesome.Icon.faw_user).apply{
            sizeDp = 24
            paddingDp = 1
            color = IconicsColor.colorInt(Color.RED)
        }

        var nome1 = "Bem-vindo " + user.Username
        var nome2 = "Lista de animes"


        val item1 = PrimaryDrawerItem().withIdentifier(1).withName(nome1).withIcon(icon)

        val item2 = SecondaryDrawerItem().withIdentifier(2).withName(nome2)

        slider.itemAdapter.add(
            item1,
            DividerDrawerItem(),
            item2,
            SecondaryDrawerItem().withName("Lista de Séries")


        )


        slider.onDrawerItemClickListener = { v, item2, position ->
            //
            val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
            startActivity(intent)
            false
        }

    }


}