package gdx.scratch;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;

	//Animation Vars
	boolean isRight;
	//Key Bools------
	boolean isDown = false;
	boolean isPunching = false;
	int nPunchState = 1;
	//---------------
	Texture txSheet;
	Animation araniDude[];
	TextureRegion trTemp;
	Sprite sprDude;
	int fW, fH, fSx, fSy;
	int nFrame, nPos;
	int nX = 100, nY = 100;

	//------------

	@Override
	public void create() {
		batch = new SpriteBatch();
		txSheet = new Texture("BlackBelt_SS.png");
		nFrame = 0;
		nPos = 0;
		araniDude = new Animation[8];
		fW = txSheet.getWidth() / 3;
		fH = txSheet.getHeight() / 8;
		for (int i = 0; i < 8; i++) {
			Sprite[] arSprDude = new Sprite[3];
			for (int j = 0; j < 3; j++) {
				fSx = j * fW;
				fSy = i * fH;
				sprDude = new Sprite(txSheet, fSx, fSy, fW, fH);
				//sprDude.setFlip(false, true);
				sprDude.setSize(200, 300);
				arSprDude[j] = new Sprite(sprDude);
			}
			araniDude[i] = new Animation(3f, arSprDude);

		}


	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 1, 1, 1); //Cyan background.
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Animation Stuff


		trTemp = (TextureRegion) araniDude[nPos].getKeyFrame(nFrame, false);
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			nX = nX -= 1;
			nPos = 3;
			nFrame++;
			if (nFrame > 9) {
				nFrame = 0;
			}
			isRight = false;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			nX = nX += 1;
			nPos = 0;
			nFrame++;
			if (nFrame > 9) {
				nFrame = 0;
			}
			isRight = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.ENTER) && isRight == false) {


			if (nPunchState == 1) {
				nPos = 4;
				if (nFrame > 12) {
					nPunchState = -1;
					nPos = 2;
				}
				nFrame++;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.ENTER) && isRight == true) {

			if (nPunchState == 1) {
				nPos = 5;
				if (nFrame > 12) {
					nPunchState = -1;
					nPos = 0;
				}
				nFrame++;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && isRight == false) {
			//nPos = 7;
			if (nFrame > 5) {
				nPos = 7;
				nFrame = 6;
			}
			nFrame++;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && isRight == true) {
			isDown = true;
			if (isDown == true) {
				if (nFrame > 5) {
					nPos = 6;
					nFrame = 6;
				}
				nFrame++;

			}

		}
		if (!Gdx.input.isKeyPressed(Input.Keys.ANY_KEY) ) {
			nFrame = 0;
			if (isRight)
				nPos = 0;
			if (!isRight)
				nPos = 2;
			//System.out.println(nFrame);
			nPunchState = 1;

		}
		batch.begin();
		batch.draw(trTemp, nX, nY);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		txSheet.dispose();
	}
}
