using System.Collections;
using System.Collections.Generic;
using System;
using System.IO;
using UnityEngine;
using UnityEngine.UI;

public class imageProcess : MonoBehaviour {

	public GameObject obj;
	public Texture2D imgOrig;
	public int imgCropX;
	public int imgCropY;
	public int imgCropW;
	public int imgCropH;

	private Texture2D imgCropped;

	private Vector2Int[] cubeFeatPt = 
	{
		new Vector2Int(30, 60),
		new Vector2Int(48, 47),
		new Vector2Int(69, 47),
		new Vector2Int(90, 57),

		new Vector2Int(35, 76),
		new Vector2Int(48, 71),
		new Vector2Int(69, 70),
		new Vector2Int(85, 75),

		new Vector2Int(58, 87),
	};

	private Vector2Int[] bkgFeatPt = 
	{
		new Vector2Int(41, 99),
		new Vector2Int(22, 86),
		new Vector2Int(12, 65),
		new Vector2Int(27, 38),
		new Vector2Int(48, 27),
		new Vector2Int(71, 26),
		new Vector2Int(95, 39),
		new Vector2Int(105, 62),
		new Vector2Int(100, 83),
		new Vector2Int(76, 98),
	};

	private int[] pCubeFeatPtVal = {0};

	// Use this for initialization
	void Start () {
		Color[] pix = imgOrig.GetPixels (imgCropX, imgCropY, imgCropW, imgCropH);
		imgCropped = new Texture2D (imgCropW, imgCropH);
		imgCropped.SetPixels (pix);
		drawFeatPoint (imgCropped);
		imgCropped.Apply ();

		//calPCubeFeatPtVal (imgCropped);

		Debug.Log (pCubeFeatPtVal);

		obj.GetComponent<Image>().sprite = Sprite.Create (imgCropped, new Rect (0, 0, imgCropW, imgCropH), new Vector2 (0.5f, 0.5f));

	}
		
	void drawFeatPoint(Texture2D texture){
		for (int i = 0; i < cubeFeatPt.Length; i++) {
			texture.SetPixel (cubeFeatPt [i].x - 1, cubeFeatPt [i].y + 1, Color.yellow);
			texture.SetPixel (cubeFeatPt [i].x, cubeFeatPt [i].y + 1, Color.yellow);
			texture.SetPixel (cubeFeatPt [i].x + 1, cubeFeatPt [i].y + 1, Color.yellow);

			texture.SetPixel (cubeFeatPt [i].x - 1, cubeFeatPt [i].y, Color.yellow);
			texture.SetPixel (cubeFeatPt [i].x, cubeFeatPt [i].y, Color.yellow);
			texture.SetPixel (cubeFeatPt [i].x + 1, cubeFeatPt [i].y, Color.yellow);

			texture.SetPixel (cubeFeatPt [i].x - 1, cubeFeatPt [i].y - 1, Color.yellow);
			texture.SetPixel (cubeFeatPt [i].x, cubeFeatPt [i].y - 1, Color.yellow);
			texture.SetPixel (cubeFeatPt [i].x + 1, cubeFeatPt [i].y - 1, Color.yellow);
		}

		for (int i = 0; i < bkgFeatPt.Length; i++) {
			texture.SetPixel (bkgFeatPt [i].x - 1, bkgFeatPt [i].y + 1, Color.blue);
			texture.SetPixel (bkgFeatPt [i].x, bkgFeatPt [i].y + 1, Color.blue);
			texture.SetPixel (bkgFeatPt [i].x + 1, bkgFeatPt [i].y + 1, Color.blue);

			texture.SetPixel (bkgFeatPt [i].x - 1, bkgFeatPt [i].y, Color.blue);
			texture.SetPixel (bkgFeatPt [i].x, bkgFeatPt [i].y, Color.blue);
			texture.SetPixel (bkgFeatPt [i].x + 1, bkgFeatPt [i].y, Color.blue);

			texture.SetPixel (bkgFeatPt [i].x - 1, bkgFeatPt [i].y - 1, Color.blue);
			texture.SetPixel (bkgFeatPt [i].x, bkgFeatPt [i].y - 1, Color.blue);
			texture.SetPixel (bkgFeatPt [i].x + 1, bkgFeatPt [i].y - 1, Color.blue);
		}
	}

	void calPCubeFeatPtVal(Texture2D texture){

		Color sum;

		for (int i = 0; i < cubeFeatPt.Length; i++) {
			sum = texture.GetPixel (cubeFeatPt [i].x - 1, cubeFeatPt [i].y + 1) +
			texture.GetPixel (cubeFeatPt [i].x, cubeFeatPt [i].y + 1) +
			texture.GetPixel (cubeFeatPt [i].x + 1, cubeFeatPt [i].y + 1) +
			texture.GetPixel (cubeFeatPt [i].x - 1, cubeFeatPt [i].y) +
			texture.GetPixel (cubeFeatPt [i].x, cubeFeatPt [i].y) +
			texture.GetPixel (cubeFeatPt [i].x + 1, cubeFeatPt [i].y) +
			texture.GetPixel (cubeFeatPt [i].x - 1, cubeFeatPt [i].y - 1) +
			texture.GetPixel (cubeFeatPt [i].x, cubeFeatPt [i].y - 1) +
			texture.GetPixel (cubeFeatPt [i].x + 1, cubeFeatPt [i].y - 1);

			pCubeFeatPtVal[i] = Mathf.RoundToInt(sum.r / 9);
		}
	}
}
