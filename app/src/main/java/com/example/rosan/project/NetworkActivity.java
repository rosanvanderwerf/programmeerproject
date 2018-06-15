package com.example.rosan.project;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import net.xqhs.graphs.graph.SimpleEdge;
import net.xqhs.graphs.graph.SimpleNode;

import org.w3c.dom.Node;

import giwi.org.networkgraph.GraphSurfaceView;
import giwi.org.networkgraph.beans.NetworkGraph;
import giwi.org.networkgraph.beans.Vertex;

public class NetworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        // get query from intent
        Intent intent = getIntent();
        String query = intent.getStringExtra("query");

        TextView tv = findViewById(R.id.network_title);
        tv.setText(query);

        // Create graph
        NetworkGraph graph = new NetworkGraph();

        // Use function to make a network based on the first x associations

        SimpleNode v1 = new SimpleNode("18");
        SimpleNode v2 = new SimpleNode("24");
        graph.getVertex().add(new Vertex(v1 , ContextCompat.getDrawable(this, R.drawable.avatar)));
        graph.getVertex().add(new Vertex(v2, ContextCompat.getDrawable(this, R.drawable.avatar)));
        graph.addEdge(new SimpleEdge(v1, v2, "12"));

        SimpleNode v3 = new SimpleNode("7");
        graph.getVertex().add(new Vertex(v3, ContextCompat.getDrawable(this, R.drawable.avatar)));
        graph.addEdge(new SimpleEdge(v2, v3, "23"));

        v1 = new SimpleNode("14");
        graph.getVertex().add(new Vertex(v1, ContextCompat.getDrawable(this, R.drawable.avatar)));
        graph.addEdge(new SimpleEdge(v3, v1, "34"));

        v1 = new SimpleNode("10");
        graph.getVertex().add(new Vertex(v1, ContextCompat.getDrawable(this, R.drawable.avatar)));
        graph.addEdge(new SimpleEdge(v3, v1, "35"));

        v1 = new SimpleNode("11");
        graph.getVertex().add(new Vertex(v1, ContextCompat.getDrawable(this, R.drawable.avatar)));
        graph.addEdge(new SimpleEdge(v1, v3, "36"));
        graph.addEdge(new SimpleEdge(v3, v1, "6"));

        GraphSurfaceView surface = findViewById(R.id.network);
        surface.init(graph);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main_menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.saved:
                Intent intent = new Intent(this, OverviewActivity.class);
                startActivity(intent);
                break;
            case R.id.about:
                break;
            case R.id.settings:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
