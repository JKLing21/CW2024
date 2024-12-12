package factories.interfaces;

import com.example.demo.Actors.Planes.Boss;
import com.example.demo.Actors.Planes.EnemyPlane;
import com.example.demo.Actors.Planes.UserPlane;
import com.example.demo.Actors.Planes.WarPlane;
import com.example.demo.Managers.AudioManager;

public interface ActorFactory {
	UserPlane createUserPlane(int initialHealth, double screenWidth, ProjectilesFactory projectilesFactory, AudioManager audioManager);

	EnemyPlane createEnemyPlane(double ScreenWidth, double initialYPos);
	
	WarPlane createWarPlane(double ScreenWidth, double initialYPos);

	Boss createBoss();
}
