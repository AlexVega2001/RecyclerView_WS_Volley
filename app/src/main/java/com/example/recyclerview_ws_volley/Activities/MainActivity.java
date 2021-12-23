package com.example.recyclerview_ws_volley.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.recyclerview_ws_volley.Adapter.Adaptador;
import com.example.recyclerview_ws_volley.Model.Evaluadores;
import com.example.recyclerview_ws_volley.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Evaluadores> eval;
    Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rcvEvaluadores);
        eval = new ArrayList<>();
        extraerEvaluadores();
    }

    private void extraerEvaluadores(){
        String json_URL = "https://www.uealecpeterson.net/ws/listadoevaluadores.php";
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest requestJson = new JsonObjectRequest(Request.Method.GET, json_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Response(response);

                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adaptador = new Adaptador(getApplicationContext(), eval);

                        adaptador.setOnclickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this,
                                                                        Activity_Evaluados.class);

                                // Obtengo el valor del idEvaluador al dar click
                                String idEvaluadores = eval.get(
                                                        recyclerView.getChildAdapterPosition(v))
                                                        .getIdEvaluador();

                                // Creamos la información a pasar entre actividades
                                Bundle b = new Bundle();
                                b.putString("idEvaluador", idEvaluadores);

                                // Añadimos la información al intent
                                intent.putExtras(b);

                                // Iniciamos la nueva actividad
                                startActivity(intent);
                            }
                        });
                        recyclerView.setAdapter(adaptador);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Error de Conexión:"+
                                error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }

        );
        queue.add(requestJson);
    }

    private void Response(JSONObject jObject){
        try {
            //Lo Guardo en un arreglo para obtener los datos por posición
            JSONArray jsonArray = new JSONArray(jObject.get("listaaevaluador").toString());

            for (int i=0 ; i < jsonArray.length(); i++){
                //Lo Guardo en un objeto el arreglo para obtener sus parámetros
                JSONObject objJson=new JSONObject(jsonArray.get(i).toString());
                Evaluadores evaluadores = new Evaluadores();
                evaluadores.setIdEvaluador(objJson.getString("idevaluador"));
                evaluadores.setName(objJson.getString("nombres"));
                evaluadores.setArea(objJson.getString("area"));
                evaluadores.setUrlPhoto(objJson.getString("imgjpg"));
                eval.add(evaluadores);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}