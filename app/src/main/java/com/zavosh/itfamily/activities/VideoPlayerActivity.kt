package com.zavosh.itfamily.activities

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.halilibo.bvpkotlin.BetterVideoPlayer
import com.zavosh.itfamily.R

class VideoPlayerActivity : AppCompatActivity() {

    lateinit var player: BetterVideoPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        val video_link = intent?.extras?.getString("video_link")

        player = findViewById(R.id.player)
        // Set the source to the HTTP URL held in the TEST_URL variable.
        // To play files, you can use Uri.fromFile(new File("..."))

        player.enableSwipeGestures(window)
        player.setSource(Uri.parse(video_link))


    }

    override fun onPause() {
        super.onPause()
        // Make sure the player stops playing if the user presses the home button.
        player.pause()
    }
}
