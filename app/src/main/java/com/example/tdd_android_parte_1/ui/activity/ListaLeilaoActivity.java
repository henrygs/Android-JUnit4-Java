package com.example.tdd_android_parte_1.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tdd_android_parte_1.R;
import com.example.tdd_android_parte_1.model.Lance;
import com.example.tdd_android_parte_1.model.Leilao;
import com.example.tdd_android_parte_1.model.Usuario;
import com.example.tdd_android_parte_1.ui.adapter.ListaLeilaoAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaLeilaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_leilao);
        ListaLeilaoAdapter adapter = new ListaLeilaoAdapter(this, leiloesDeExemplo());
        RecyclerView recyclerView = findViewById(R.id.lista_leilao_recyclerview);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ListaLeilaoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Leilao leilao) {
                Intent vaiParaLancesLeilao = new Intent(ListaLeilaoActivity.this, LancesLeilaoActivity.class);
                vaiParaLancesLeilao.putExtra("leilao", leilao);
                startActivity(vaiParaLancesLeilao);
            }
        });
    }
    private List<Leilao> leiloesDeExemplo() {
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Henrique"), 200.0));
        console.propoe(new Lance(new Usuario("Zequinha"), 300.0));
        return new ArrayList<>(Arrays.asList(
                console
        ));
    }
}