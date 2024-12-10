package factories.interfaces;

import com.example.demo.Boss;
import com.example.demo.EnemyPlane;
import com.example.demo.UserPlane;

public interface ActorFactory {
	UserPlane createUserPlane(int initialHealth, double screenWidth, ProjectilesFactory projectilesFactory);

	EnemyPlane createEnemyPlane(double ScreenWidth, double initialYPos);

	Boss createBoss();
}
