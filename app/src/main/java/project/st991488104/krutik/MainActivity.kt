package project.st991488104.krutik
//Krutik Parikh | 991488104
//991435185 Nathaniel Kawal
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import project.st991488104.krutik.databinding.ActivityMainBinding
import project.st991488104.krutik.interfaces.DrawerController


class MainActivity : AppCompatActivity() , DrawerController {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        drawerLayout = binding.drawerLayout

        setupActionBarWithNavController(findNavController(R.id.navHostFragment))

        val navController = this.findNavController(R.id.navHostFragment)
        NavigationUI.setupWithNavController(binding.navView, navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHostFragment)

        return navController.navigateUp() || super.onSupportNavigateUp() || NavigationUI.navigateUp(navController, drawerLayout)


    }

    override fun setDrawer_Locked() {
       drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

    }

    override fun setDrawer_Unlocked() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }
}