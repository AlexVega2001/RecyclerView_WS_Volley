package com.example.recyclerview_ws_volley.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.recyclerview_ws_volley.Adapter.AdaptadorEvaluar;
import com.example.recyclerview_ws_volley.Model.Evaluados;
import com.example.recyclerview_ws_volley.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Activity_Evaluados extends AppCompatActivity {

    private RecyclerView recyclerView2;
    private List<Evaluados> evaluar;
    private AdaptadorEvaluar adaptEvaluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluados);

        // Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();
        Toast.makeText(this,"ID EVALUADOR: "+bundle.getString("idEvaluador"),
                        Toast.LENGTH_SHORT).show();

        recyclerView2 = findViewById(R.id.rcvEvaluados);
        evaluar = new ArrayList<>();
        extraerEvaluados(bundle.getString("idEvaluador"));
    }

    private void extraerEvaluados(String idEvaluador){
        String json_URL = "https://uealecpeterson.net/ws/listadoaevaluar.php?e=" + idEvaluador;
        RequestQueue queue2 = Volley.newRequestQueue(this);
        JsonObjectRequest requestJson2 = new JsonObjectRequest(Request.Method.GET, json_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        responseObject(response);

                        recyclerView2.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adaptEvaluar = new AdaptadorEvaluar(getApplicationContext(), evaluar);
                        recyclerView2.setAdapter(adaptEvaluar);
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
        queue2.add(requestJson2);
    }

    private void responseObject(JSONObject jsonObject){
        try {
            //Lo Guardo en un arreglo para obtener los datos por posición
            JSONArray jsonArray = new JSONArray(jsonObject.get("listaaevaluar").toString());

            for (int i=0 ; i < jsonArray.length(); i++){
                //Lo Guardo en un objeto el arreglo para obtener sus parámetros
                JSONObject objJson=new JSONObject(jsonArray.get(i).toString());
                Evaluados evalclass= new Evaluados();
                evalclass.setNombres(objJson.getString("Nombres"));
                evalclass.setFechaEvaInicio(objJson.getString("fechainicio"));
                evalclass.setCargo(objJson.getString("cargo"));
                evalclass.setRelacionDep(objJson.getString("situacion"));
                evalclass.setUrl(objJson.getString("imgJPG"));
                evaluar.add(evalclass);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}