package com.example.multiplicacion;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Variables para los elementos de la UI (Interfaz de Usuario)
    private EditText etNumero1;
    private EditText etNumero2;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enlaza la actividad con el diseño XML (activity_main.xml)
        setContentView(R.layout.activity_main);

        // 1. Inicializar variables enlazándolas con los IDs del XML
        etNumero1 = findViewById(R.id.etNumero1);
        etNumero2 = findViewById(R.id.etNumero2);
        tvResultado = findViewById(R.id.tvResultado);

        // Enlazar los botones de operación usando sus IDs
        Button btnSumar = findViewById(R.id.btnSumar);
        Button btnRestar = findViewById(R.id.btnRestar);
        Button btnMultiplicar = findViewById(R.id.btnMultiplicar);
        Button btnDividir = findViewById(R.id.btnDividir);

        // 2. Asignar listeners (acciones al hacer clic) a los botones.
        // Cada botón llama a la misma función 'calcularResultado' pero con un operador diferente.
        btnSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularResultado('+');
            }
        });

        btnRestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularResultado('-');
            }
        });

        btnMultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularResultado('x');
            }
        });

        btnDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularResultado('/');
            }
        });
    }

    /**
     * Función que obtiene los números, realiza la operación matemática y actualiza la UI.
     * @param operacion El carácter que define la operación ('+', '-', 'x', '/')
     */
    private void calcularResultado(char operacion) {
        // Obtener el texto de los EditText
        String num1Str = etNumero1.getText().toString();
        String num2Str = etNumero2.getText().toString();

        // 1. Validación: Si algún campo está vacío, muestra un mensaje y termina.
        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(this, "Por favor, introduce ambos números.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 2. Convertir el texto a números de doble precisión (Double)
        double num1;
        double num2;
        try {
            num1 = Double.parseDouble(num1Str);
            num2 = Double.parseDouble(num2Str);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Error: formato de número inválido.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 3. Realizar la operación
        double resultado = 0.0;
        boolean divisionPorCero = false;

        switch (operacion) {
            case '+':
                resultado = num1 + num2;
                break;
            case '-':
                resultado = num1 - num2;
                break;
            case 'x':
                resultado = num1 * num2;
                break;
            case '/':
                if (num2 != 0.0) {
                    resultado = num1 / num2;
                } else {
                    // Manejar el caso especial de división por cero
                    Toast.makeText(this, "No se puede dividir por cero.", Toast.LENGTH_LONG).show();
                    tvResultado.setText("Error: Div / 0");
                    divisionPorCero = true;
                }
                break;
        }

        // 4. Mostrar el resultado
        if (!divisionPorCero) {
            // Muestra el resultado formateado en el TextView
            tvResultado.setText("Resultado: " + resultado);
        }
    }
}