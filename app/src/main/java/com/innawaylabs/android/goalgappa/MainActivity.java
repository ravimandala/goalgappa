package com.innawaylabs.android.goalgappa;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.innawaylabs.android.goalgappa.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

	private ActivityMainBinding binding;
	int cursor = 0;
	char[] letters;
	String[] words = {"WORD", "GRID", "DRUM", "MAPS", "SPAM", "EGGS"};
	int currentWord;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
		shuffleWord();
	}

	private void shuffleWord() {
		currentWord = Math.abs(new Random().nextInt()) % words.length;

		letters = words[currentWord].toCharArray();
		binding.btnPick1.setText(String.valueOf(letters[0]));
		binding.btnPick2.setText(String.valueOf(letters[1]));
		binding.btnPick3.setText(String.valueOf(letters[2]));
		binding.btnPick4.setText(String.valueOf(letters[3]));
	}

	private void pickLetter(String letter) {
		switch(cursor % 4) {
			case 0:
				binding.btnPicked1.setText(letter);
				break;
			case 1:
				binding.btnPicked2.setText(letter);
				break;
			case 2:
				binding.btnPicked3.setText(letter);
				break;
			case 3:
				binding.btnPicked4.setText(letter);
				break;
		}
		cursor++;
	}

	public void buttonClicked(View view) {
		switch(view.getId()) {
			case R.id.btnPicked1:
			case R.id.btnPicked2:
			case R.id.btnPicked3:
			case R.id.btnPicked4:
				((Button)view).setText("");
				break;
			case R.id.btnPick1:
			case R.id.btnPick2:
			case R.id.btnPick3:
			case R.id.btnPick4:
				pickLetter(((Button) view).getText().toString());
				break;
			case R.id.btnSubmit:
				String result = binding.btnPicked1.getText().toString() +
						binding.btnPicked2.getText().toString() +
						binding.btnPicked3.getText().toString() +
						binding.btnPicked4.getText().toString();
				if (result.equals(words[currentWord])) {
					Toast.makeText(this, "You are correct!!!", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(this, "Try again!!!", Toast.LENGTH_SHORT).show();
				}
				shuffleWord();
				break;
			default:
				Toast.makeText(this, "404 - Page Not Found", Toast.LENGTH_SHORT).show();
		}
	}
}
