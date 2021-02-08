package br.com.interativo.teste

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import br.com.interativo.teste.databinding.ActivityMainBinding
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
        setContentView(view)

        var usuario = intent.extras?.getParcelable<userClass>("usuario")!!



        setContentView(R.layout.activity_second)

        funteste(usuario)
    }

    fun funteste(user: userClass){

        var db = dataBaseHandler(this@SecondActivity)

        val icon = IconicsDrawable(this@SecondActivity, FontAwesome.Icon.faw_user).apply{
            sizeDp = 24
            paddingDp = 1
            color = IconicsColor.colorInt(Color.RED)
        }

        val item1 = PrimaryDrawerItem().withIdentifier(1).withName("Bem-vindo\n" + user).withIcon(icon)

        val item2 = SecondaryDrawerItem().withIdentifier(2).withName("SubMenu")


        val teste = PrimaryDrawerItem().apply {
            identifier = 1
            name = StringHolder("Teste")
        }

        slider.itemAdapter.add(
            item1,
            DividerDrawerItem(),
            item2,
            SecondaryDrawerItem().withName("Maracuja")

        )


        slider.onDrawerItemClickListener = { v, item1, position ->
            //


            false
        }

    }


}