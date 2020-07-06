package is.symphony.collegeinternship.olympicgames.repositories;

import is.symphony.collegeinternship.olympicgames.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    public Admin findAdminByUserName(String userName);
}
