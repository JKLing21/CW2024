package factories.interfaces;

import com.example.demo.BossProjectile;
import com.example.demo.EnemyProjectile;
import com.example.demo.UserProjectile;

public interface ProjectilesFactory {
	UserProjectile createUserProjectile(double initialXPos, double initialYPos, double screenWidth);

	EnemyProjectile createEnemyProjectile(double initialX, double initialY);

	BossProjectile createBossProjectile(double initialY);
}
