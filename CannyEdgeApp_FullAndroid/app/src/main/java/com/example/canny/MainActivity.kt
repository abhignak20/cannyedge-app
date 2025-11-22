package com.example.canny

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import org.opencv.android.OpenCVLoader
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize OpenCV
        val ok = OpenCVLoader.initDebug()
        if (!ok) {
            // In production use OpenCVLoader.initAsync with proper callback
            throw RuntimeException("OpenCV failed to initialize")
        }

        val bmp = BitmapFactory.decodeResource(resources, R.drawable.sample)
        val mat = Mat(bmp.height, bmp.width, CvType.CV_8UC4)
        bmp.copyPixelsToBuffer(java.nio.ByteBuffer.allocate(bmp.byteCount).also { buf -> bmp.copyPixelsToBuffer(buf); buf.rewind() })

        // Convert to gray and run Canny using OpenCV Java API
        val rgba = Mat()
        val gray = Mat()
        org.opencv.android.Utils.bitmapToMat(bmp, rgba)
        Imgproc.cvtColor(rgba, gray, Imgproc.COLOR_RGBA2GRAY)
        val edges = Mat()
        Imgproc.Canny(gray, edges, 80.0, 150.0)
        Imgproc.cvtColor(edges, rgba, Imgproc.COLOR_GRAY2RGBA)

        val outBmp = Bitmap.createBitmap(rgba.cols(), rgba.rows(), Bitmap.Config.ARGB_8888)
        org.opencv.android.Utils.matToBitmap(rgba, outBmp)

        findViewById<ImageView>(R.id.imageView).setImageBitmap(outBmp)
    }
}
