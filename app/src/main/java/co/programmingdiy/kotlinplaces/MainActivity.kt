package co.programmingdiy.kotlinplaces

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinner.adapter = createPlacesAdapter()
        btn_Find.setOnClickListener { findPlaceNearMe() }
    }

    /**
     * Set up Places Array Adapter
     */
    fun createPlacesAdapter(): ArrayAdapter<CharSequence> {
        //Create our places ArrayAdapter using our defined items
        val placesArrayAdapter: ArrayAdapter<CharSequence> = ArrayAdapter
                .createFromResource(this,
                        R.array.places,
                        android.R.layout.simple_spinner_dropdown_item
                )
        //Set the layout to use when the list of places appears
        placesArrayAdapter
                .setDropDownViewResource(
                        android.R.layout.simple_dropdown_item_1line
                )
        return placesArrayAdapter
    }

    /**
     * Find places using Google Maps Intent
     */
    fun findPlaceNearMe() {
        //Create uri to send to Google Maps
        val uriString = "geo:0,0?q=" +
                spinner.selectedItem.toString().decapitalize()
        val googleMapsIntentUri: Uri = Uri.parse(uriString)
        //Set uri to the Intent to try to launch Google Maps
        val googleMapsIntent = Intent(Intent.ACTION_VIEW, googleMapsIntentUri)
        googleMapsIntent.setPackage("com.google.android.apps.maps")
        //Launch Google Maps with our parameters
        startActivity(googleMapsIntent)
    }
}
