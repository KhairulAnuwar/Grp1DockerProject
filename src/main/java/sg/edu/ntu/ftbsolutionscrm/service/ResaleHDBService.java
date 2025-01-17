package sg.edu.ntu.ftbsolutionscrm.service;

import sg.edu.ntu.ftbsolutionscrm.entity.ResaleHdb;

import java.util.List;

public interface ResaleHDBService {

    List<ResaleHdb> getAllResaleHDB();

    ResaleHdb getResaleHDBById(Long id);

    ResaleHdb updatResaleHDB(Long id, ResaleHdb resaleHDB);

    ResaleHdb createResaleHDB(ResaleHdb resaleHDB);

    public void deleteResaleHDB(Long id);
}
