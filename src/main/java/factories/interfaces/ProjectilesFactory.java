package factories.interfaces;

import com.example.demo.Actors.Projectiles.BossProjectile;
import com.example.demo.Actors.Projectiles.EnemyProjectile;
import com.example.demo.Actors.Projectiles.UserProjectile;
import com.example.demo.Actors.Projectiles.WarPlaneProjectile;

public interface ProjectilesFactory {
	UserProjectile createUserProjectile(double initialXPos, double initialYPos, double screenWidth);

	EnemyProjectile createEnemyProjectile(double initialX, double initialY);

	BossProjectile createBossProjectile(double initialY);
	
	WarPlaneProjectile createWarplaneProjectile(double initialXPos, double initialYPos);
}
