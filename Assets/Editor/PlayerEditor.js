#pragma strict

@CustomEditor (scully.player.Player)
class PlayerEditor extends Editor{
	function OnInspectorGUI(){
		var myPlayer : scully.player.Player = target;

		myPlayer.fireRate = EditorGUILayout.FloatField ("Fire Rate", myPlayer.fireRate);
		myPlayer.bullet = EditorGUILayout.ObjectField ("Bullet", myPlayer.bullet, typeof(GameObject));
	}
}