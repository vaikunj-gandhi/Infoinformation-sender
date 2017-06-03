package com.example.aum.smartyapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;

import java.util.ArrayList;

public class BusRoute extends AppCompatActivity {
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_route);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        searchView = (SearchView) findViewById(R.id.mSearch);
        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view);
        //SET ITS PROPETRIES
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        //ADAPTER
        final MyAdapterbus adapter = new MyAdapterbus(this, getPlayers());
        rv.setAdapter(adapter);
        //SEARCH
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                //FILTER AS YOU TYPE
                adapter.getFilter().filter(query);
                return false;
            }
        });
    }

    //ADD PLAYERS TO ARRAYLIST
    private ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        Player p = new Player();
        p.setName("Route 11 (AYURVEDIK CROSS ROAD)");
        p.setPos("Aayurvedik Cross Road → Swaminarayan Nagar → Ram Vatica → Poonam Complex → Kala Darshan → Vadeshvar Hanuman Mandir → Parivar Char Rasta # → Vrundavan Char Rasta # → Prabhat → Ring Road → Airport Circle → Amitnagar char rasta → Sama Talav → Savli College");

        players.add(p);
        p = new Player();
        p.setName("Route 12 (PRABHAT COLONY)");
        p.setPos("Prabhat Colony → Uma cross road → Mahavir Hall → Kishan Vadi → Premdas Jalaram → Panchsheel → Dhaval char rasta → Sangam Cross Road → Gandhi Park → Airport Circle → Amitnagar Char Rasta → Sama Talav → Savli College");

        players.add(p);
        p = new Player();
        p.setName("Route 13 (SUSEN CHAR RASTA)");
        p.setPos("Susen Char Rasta → Akashwani → Kabir Complex → Bhavans School → Baroda Dairy → Hazira → Pratapnagar Bridge → Zenith → Soma Talav → Gurukul → Parivar Char Rasta → Vrundavan Char Rasta → Sardar Estate → Khodiyar Nagar → Vaikunth → Airport Circle → Amitnagar Char Rasta → Sama Talav → Savli College");

        players.add(p);
        p = new Player();
        p.setName("Route 14 (VADSAR BRIDGE)");
        p.setPos("Vadsar Bridge → Darbar Chowkdi → Suncity circle → Smasan Char Rasta → Avdhut Fatak → Amitnagar char rasta → Sama Talav → Savli College");

        players.add(p);
        p = new Player();
        p.setName("Route 15 (BAGIKHANA)");
        p.setPos("Bagikhana → Kirti Stambh → Kothi Char Rasta → Salatwada → Nagarvada → Bhutdizapa → Jivan Bharti → Muktanand → Watertank → Amitnagar char rasta → Sama Talav → Savli College");

        players.add(p);
        p = new Player();
        p.setName("Route16 (YESH COMPLEX)");
        p.setPos("Yash Complex → Kalp Vruksh → Harinagar → Race Course Circle → Genda Circle → Fatehgunj Char Rasta → Amitnagar char rasta → Sama Talav → Savli College");

        players.add(p);
        p = new Player();
        p.setName("Route 17 (TIME CIRCLE)");
        p.setPos("Time Circle → Vasna Road → Raneshwar → Tandalja → Rajiv Road → Manisha Cross Road → Havmor → Chakli Circle → Genda Circle → Amitnagar Char Rasta → Sama Talav → Savli College");

        players.add(p);
        p = new Player();
        p.setName("Route 18 (DASHAMA TEMPLE)");
        p.setPos("Dashama Temple → Aakanksha Duplex → Samta Char Rasta → Suresh Bhajiya house → Jhansi Ki Rani → Reliance Petrol pump → Ganga Jamuna → Ellora Park → Genda Circle → Fatehgunj Char Rasta → Nizampura → Delux Char Rasta → Sama talav → Savli College");

        players.add(p);
        p = new Player();
        p.setName("Route 19 (UNDERA)");
        p.setPos("Undera → Petrofils → Panchvati → Sahyog → I.T.I. → Bapu Ni Dargah → Gujarat High Board → Genda Circle → Fatehgunj → Nizampura → Delux Char Rasta → Military Boys Hostel → Sama Talav → Savli College");

        players.add(p);
        p = new Player();
        p.setName("Route 20 (CHHANI ROAD)");
        p.setPos("Chhani → Ramakaka ni Dairy → Keya Motors → Chhani Jakatnaka → Abhilasha char rasta → Sama talav → Savli College");

        players.add(p);
        p = new Player();
        p.setName("Route 21 (SOMA TALAV)");
        p.setPos("Soma Talav → Narayan School → Parivaar char rasta → Vrundavan char rasta → Sardar Estate → Khodiyar Nagar → Airport Circle → Amitnagar char rasta → Sama talav → Savli College");

        players.add(p);
        p = new Player();
        p.setName("Route 22 (ANAND)");
        p.setPos("Bakrol Gate → Vaibhav Cinema → Raghuvir Chembers → Bhaikaka → APC → Town Hall → New Bus Stop → Baggi → Borsad Chowkdi → Ganesh Chowkdi → Chikhodra Chowkdi → Bedva → Sarsa → Khambholaj → Savli College");
        players.add(p);
        p = new Player();
        p.setName("Route 24 (TULSIDHAM)");
        p.setPos("Tulsi Dham char rasta → Saraswati complex → Ramesh Patel (Manjalpur) → Rajmahal Road → Salatwada → Nagarwada → Amitnagar char rasta → Sama talav → Savli College");
        players.add(p);

        p = new Player();
        p.setName("Route 26 (TARSALI)");
        p.setPos("Tarsali (Ganga Sagar) → Tarsali (Market) → Tarsali Mandir → Soma Talav → Vrundavan char rasta → New Ring Road → Amitnagar char rasta → Sama Talav → Savli College");

        p = new Player();
        p.setName("Route 27 (AKSHAR CHOWK)");
        p.setPos("Akshar Chowk → Mujmahuda → Akota Garden → Cow Circle → Havmore → Genda Circle → Amitnagar char rasta → Sama talav → Savli College");

        p = new Player();
        p.setName("Route 29 (MILITARY BOYS HOSTEL)");
        p.setPos("Military Boys Hostel → Jal Jyot → Chanakyapuri → Sama Garden → Sama Talav → Savli College");

        p = new Player();
        p.setName("Route 31 (MANEJA)");
        p.setPos("Gayatri Nagar → Maneja → Maneja Crossing → Makarpura → Hunuman Temple → Novino → Susen → Tarsali → Soma Talav → Vrundavan char rasta → Ring Road → Airport Circle → Amitnagar char rasta → Sama Talav → Savli College");

        p = new Player();
        p.setName("Route 32 (NADIAD)");
        p.setPos("Nadiad S.T. Bus Stop → Santram Mandir → City Point → Maha Gujarat → Vaniavad → Uttarsanda → Chaklasi → Kasor → Bhalej → Ode Chaukdi # → Shili → Ahima → Savli College");

        p = new Player();
        p.setName("Route 33 (UMRETH - DAKOR)");
        p.setPos("Dakor Bus Stand → Umreth → Bechari → Sureli → Sundalpura → Savli College");

        players.add(p);
        return players;
    }
}