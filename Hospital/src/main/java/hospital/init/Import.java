package hospital.init;

import hospital.dao.DepartureDao;
import hospital.dao.DiagnosisDao;
import hospital.dao.PeopleDao;
import hospital.dao.WardDao;
import hospital.types.Diagnosis;
import hospital.types.People;
import hospital.types.Ward;

import java.util.ArrayList;
import java.util.List;

/**
 * Polytech
 * Created by igor on 06.05.17.
 */

public class Import {
    public static final long TEN_PLACES = 10L;
    public static final long FIVE_PLACES = 5L;
    PeopleDao peopleDao;
    DiagnosisDao diagnosisDao;
    DepartureDao departureDao;
    WardDao wardDao;
    private final List<Ward> wards = new ArrayList<>();
    private final List<Diagnosis> diagnoses = new ArrayList<>();
    public Import(final PeopleDao peopleDao, final DiagnosisDao diagnosisDao, final DepartureDao departureDao, final WardDao wardDao) {
        this.peopleDao = peopleDao;
        this.diagnosisDao = diagnosisDao;
        this.departureDao = departureDao;
        this.wardDao = wardDao;
    }

    public void init() {
        wards.add(wardDao.insert(Ward.builder().wardName("10").maxCount(TEN_PLACES).departureId(departureDao.findByName("Терапевтическое")).build()));
        wards.add(wardDao.insert(Ward.builder().wardName("11").maxCount(FIVE_PLACES).departureId(departureDao.findByName("Терапевтическое")).build()));
        wards.add(wardDao.insert(Ward.builder().wardName("12").maxCount(FIVE_PLACES).departureId(departureDao.findByName("Терапевтическое")).build()));
        wards.add(wardDao.insert(Ward.builder().wardName("15").maxCount(TEN_PLACES).departureId(departureDao.findByName("Хирургическое")).build()));
        wards.add(wardDao.insert(Ward.builder().wardName("16").maxCount(FIVE_PLACES).departureId(departureDao.findByName("Хирургическое")).build()));
        wards.add(wardDao.insert(Ward.builder().wardName("17").maxCount(FIVE_PLACES).departureId(departureDao.findByName("Хирургическое")).build()));
        wards.add(wardDao.insert(Ward.builder().wardName("20").maxCount(TEN_PLACES).departureId(departureDao.findByName("Реанимации")).build()));
        wards.add(wardDao.insert(Ward.builder().wardName("21").maxCount(FIVE_PLACES).departureId(departureDao.findByName("Реанимации")).build()));
        wards.add(wardDao.insert(Ward.builder().wardName("22").maxCount(FIVE_PLACES).departureId(departureDao.findByName("Реанимации")).build()));
        wards.add(wardDao.insert(Ward.builder().wardName("30").maxCount(TEN_PLACES).departureId(departureDao.findByName("Неврологическое")).build()));
        wards.add(wardDao.insert(Ward.builder().wardName("31").maxCount(FIVE_PLACES).departureId(departureDao.findByName("Неврологическое")).build()));
        wards.add(wardDao.insert(Ward.builder().wardName("32").maxCount(FIVE_PLACES).departureId(departureDao.findByName("Неврологическое")).build()));
        wards.add(wardDao.insert(Ward.builder().wardName("37").maxCount(TEN_PLACES).departureId(departureDao.findByName("Гинекологическое")).build()));
        wards.add(wardDao.insert(Ward.builder().wardName("38").maxCount(FIVE_PLACES).departureId(departureDao.findByName("Гинекологическое")).build()));
        wards.add(wardDao.insert(Ward.builder().wardName("39").maxCount(FIVE_PLACES).departureId(departureDao.findByName("Гинекологическое")).build()));
        wards.add(wardDao.insert(Ward.builder().wardName("45").maxCount(TEN_PLACES).departureId(departureDao.findByName("Травматологическое")).build()));
        wards.add(wardDao.insert(Ward.builder().wardName("46").maxCount(FIVE_PLACES).departureId(departureDao.findByName("Травматологическое")).build()));
        wards.add(wardDao.insert(Ward.builder().wardName("47").maxCount(FIVE_PLACES).departureId(departureDao.findByName("Травматологическое")).build()));
        wards.add(wardDao.insert(Ward.builder().wardName("52").maxCount(TEN_PLACES).departureId(departureDao.findByName("Ревматологическое")).build()));
        wards.add(wardDao.insert(Ward.builder().wardName("53").maxCount(FIVE_PLACES).departureId(departureDao.findByName("Ревматологическое")).build()));
        wards.add(wardDao.insert(Ward.builder().wardName("54").maxCount(FIVE_PLACES).departureId(departureDao.findByName("Ревматологическое")).build()));

        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("ОРВИ").departureId(departureDao.findByName("Терапевтическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Бронхит").departureId(departureDao.findByName("Терапевтическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Сердечно-сосудистая").departureId(departureDao.findByName("Терапевтическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Искривление позвоночника").departureId(departureDao.findByName("Терапевтическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Диатез").departureId(departureDao.findByName("Терапевтическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Анемия").departureId(departureDao.findByName("Терапевтическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Опухоль").departureId(departureDao.findByName("Хирургическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Воспаление легких").departureId(departureDao.findByName("Хирургическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Порок сердца").departureId(departureDao.findByName("Хирургическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Грыжа").departureId(departureDao.findByName("Хирургическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Запор").departureId(departureDao.findByName("Хирургическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Варикоз вен").departureId(departureDao.findByName("Хирургическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Нейроинфекция").departureId(departureDao.findByName("Реанимации")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Пневмония").departureId(departureDao.findByName("Реанимации")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Лептоспироз").departureId(departureDao.findByName("Реанимации")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Гепатит").departureId(departureDao.findByName("Реанимации")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Сальмоноллез").departureId(departureDao.findByName("Реанимации")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Гастроэнтерит").departureId(departureDao.findByName("Реанимации")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Лицевая боль").departureId(departureDao.findByName("Неврологическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Головная боль").departureId(departureDao.findByName("Неврологическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Судороги").departureId(departureDao.findByName("Неврологическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Паралич Бэлла").departureId(departureDao.findByName("Неврологическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Тремор").departureId(departureDao.findByName("Неврологическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Остеохондроз").departureId(departureDao.findByName("Неврологическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Воспаление органов").departureId(departureDao.findByName("Гинекологическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Воспаление придат").departureId(departureDao.findByName("Гинекологическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Киндидоз").departureId(departureDao.findByName("Гинекологическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Тримохомониаз").departureId(departureDao.findByName("Гинекологическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Цитомегаловирус").departureId(departureDao.findByName("Гинекологическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Герпес").departureId(departureDao.findByName("Гинекологическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Артрит").departureId(departureDao.findByName("Травматологическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Артроз").departureId(departureDao.findByName("Травматологическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Кифоз").departureId(departureDao.findByName("Травматологическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Лордоз").departureId(departureDao.findByName("Травматологическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Некроз").departureId(departureDao.findByName("Травматологическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Сколиоз").departureId(departureDao.findByName("Травматологическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Подагра").departureId(departureDao.findByName("Ревматологическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Остеопороз").departureId(departureDao.findByName("Ревматологическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Остеоартроз").departureId(departureDao.findByName("Ревматологическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Узловатая эритема").departureId(departureDao.findByName("Ревматологическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Полиомиозит").departureId(departureDao.findByName("Ревматологическое")).build()));
        diagnoses.add(diagnosisDao.insert(Diagnosis.builder().diagnosisName("Дерматомиозит").departureId(departureDao.findByName("Ревматологическое")).build()));

        peopleDao.insert(People.builder().firstName("Павел").lastName("Попов").fatherName("Дмитриевич").diagnosisId(diagnoses.get(0).getDiagnosisId()).wardId(wards.get(0).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Никита").lastName("Павлов").fatherName("Павлович").diagnosisId(diagnoses.get(2-1).getDiagnosisId()).wardId(wards.get(0).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Андрей").lastName("Никитов").fatherName("Никитьевич").diagnosisId(diagnoses.get(0).getDiagnosisId()).wardId(wards.get(0).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Сергей").lastName("Адреев").fatherName("Андреевич").diagnosisId(diagnoses.get(2-1).getDiagnosisId()).wardId(wards.get(0).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Степан").lastName("Сергеев").fatherName("Сергеевич").diagnosisId(diagnoses.get(0).getDiagnosisId()).wardId(wards.get(0).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Михаил").lastName("Степанов").fatherName("Степанович").diagnosisId(diagnoses.get(3-1).getDiagnosisId()).wardId(wards.get(1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Игорь").lastName("Михаилов").fatherName("Михайлович").diagnosisId(diagnoses.get(4-1).getDiagnosisId()).wardId(wards.get(2-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Антон").lastName("Игорев").fatherName("Игоревич").diagnosisId(diagnoses.get(3-1).getDiagnosisId()).wardId(wards.get(2-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Дмитрий").lastName("Антонов").fatherName("Антонович").diagnosisId(diagnoses.get(5-1).getDiagnosisId()).wardId(wards.get(3-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Василий").lastName("Дмитриев").fatherName("Дмитриевич").diagnosisId(diagnoses.get(6-1).getDiagnosisId()).wardId(wards.get(3-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Борис").lastName("Васильев").fatherName("Васильевич").diagnosisId(diagnoses.get(6-1).getDiagnosisId()).wardId(wards.get(3-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Богдан").lastName("Борисов").fatherName("Борисович").diagnosisId(diagnoses.get(7-1).getDiagnosisId()).wardId(wards.get(4-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Вадим").lastName("Богданов").fatherName("Богданович").diagnosisId(diagnoses.get(8-1).getDiagnosisId()).wardId(wards.get(4-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Булат").lastName("Вадимов").fatherName("Вадимович").diagnosisId(diagnoses.get(8-1).getDiagnosisId()).wardId(wards.get(4-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Валерий").lastName("Булатов").fatherName("Булатович").diagnosisId(diagnoses.get(7-1).getDiagnosisId()).wardId(wards.get(4-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Виктор").lastName("Валерьев").fatherName("Валерьевич").diagnosisId(diagnoses.get(9-1).getDiagnosisId()).wardId(wards.get(4-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Владислав").lastName("Викторов").fatherName("Викторович").diagnosisId(diagnoses.get(10-1).getDiagnosisId()).wardId(wards.get(5-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Всеволод").lastName("Владиславов").fatherName("Владиславович").diagnosisId(diagnoses.get(10-1).getDiagnosisId()).wardId(wards.get(5-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Вячеслав").lastName("Всеволодов").fatherName("Всеволодович").diagnosisId(diagnoses.get(10-1).getDiagnosisId()).wardId(wards.get(5-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Виталий").lastName("Вяеславов").fatherName("Вячеславович").diagnosisId(diagnoses.get(11-1).getDiagnosisId()).wardId(wards.get(6-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Геннадий").lastName("Витальев").fatherName("Витальевич").diagnosisId(diagnoses.get(11-1).getDiagnosisId()).wardId(wards.get(6-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Глеб").lastName("Геннадьев").fatherName("Геннадьевич").diagnosisId(diagnoses.get(12-1).getDiagnosisId()).wardId(wards.get(6-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Герман").lastName("Глебов").fatherName("Глебович").diagnosisId(diagnoses.get(13-1).getDiagnosisId()).wardId(wards.get(7-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Георгий").lastName("Германов").fatherName("Германович").diagnosisId(diagnoses.get(13-1).getDiagnosisId()).wardId(wards.get(7-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Григорий").lastName("Георгев").fatherName("Георгевич").diagnosisId(diagnoses.get(13-1).getDiagnosisId()).wardId(wards.get(7-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Давид").lastName("Григорьев").fatherName("Григорьевич").diagnosisId(diagnoses.get(14-1).getDiagnosisId()).wardId(wards.get(7-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Даниил").lastName("Давидов").fatherName("Давидович").diagnosisId(diagnoses.get(15-1).getDiagnosisId()).wardId(wards.get(7-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Дамир").lastName("Даниилов").fatherName("Даниилович").diagnosisId(diagnoses.get(16-1).getDiagnosisId()).wardId(wards.get(8-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Демьян").lastName("Дамиров").fatherName("Дамирович").diagnosisId(diagnoses.get(16-1).getDiagnosisId()).wardId(wards.get(8-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Денис").lastName("Денисов").fatherName("Денисович").diagnosisId(diagnoses.get(17-1).getDiagnosisId()).wardId(wards.get(8-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Евгений").lastName("Денисов").fatherName("Денисович").diagnosisId(diagnoses.get(18-1).getDiagnosisId()).wardId(wards.get(9-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Евдоким").lastName("Евгеньев").fatherName("Евгеньевич").diagnosisId(diagnoses.get(18-1).getDiagnosisId()).wardId(wards.get(9-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Егор").lastName("Евдокимов").fatherName("Евдокимович").diagnosisId(diagnoses.get(18-1).getDiagnosisId()).wardId(wards.get(9-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Емельян").lastName("Егоров").fatherName("Егорович").diagnosisId(diagnoses.get(19-1).getDiagnosisId()).wardId(wards.get(10-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Елисей").lastName("Емельянов").fatherName("Емельянович").diagnosisId(diagnoses.get(19-1).getDiagnosisId()).wardId(wards.get(10-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Еремей").lastName("Елисеев").fatherName("Елисеевич").diagnosisId(diagnoses.get(19-1).getDiagnosisId()).wardId(wards.get(10-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Захар").lastName("Еремеев").fatherName("Еремеевич").diagnosisId(diagnoses.get(20-1).getDiagnosisId()).wardId(wards.get(10-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Ибрагим").lastName("Захаров").fatherName("Захарович").diagnosisId(diagnoses.get(21-1).getDiagnosisId()).wardId(wards.get(10-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Камиль").lastName("Ибрагимов").fatherName("Ибрагимович").diagnosisId(diagnoses.get(22-1).getDiagnosisId()).wardId(wards.get(11-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Игнат").lastName("Камильев").fatherName("Камильевич").diagnosisId(diagnoses.get(23-1).getDiagnosisId()).wardId(wards.get(11-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Игорь").lastName("Игнатьев").fatherName("Игнатьевич").diagnosisId(diagnoses.get(24-1).getDiagnosisId()).wardId(wards.get(11-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Ильгиз").lastName("Игорев").fatherName("Игоревич").diagnosisId(diagnoses.get(24-1).getDiagnosisId()).wardId(wards.get(12-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Ильшат").lastName("Ильгизов").fatherName("Ильгизович").diagnosisId(diagnoses.get(23-1).getDiagnosisId()).wardId(wards.get(12-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Илья").lastName("Ильшатов").fatherName("Ильшатович").diagnosisId(diagnoses.get(22-1).getDiagnosisId()).wardId(wards.get(12-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Иннокентий").lastName("Ильев").fatherName("Ильевич").diagnosisId(diagnoses.get(25-1).getDiagnosisId()).wardId(wards.get(13-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Исаак").lastName("Иннокентьев").fatherName("Иннокентьевич").diagnosisId(diagnoses.get(26-1).getDiagnosisId()).wardId(wards.get(13-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Лев").lastName("Исааков").fatherName("Исаакьевич").diagnosisId(diagnoses.get(26-1).getDiagnosisId()).wardId(wards.get(13-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Максим").lastName("Львов").fatherName("Левович").diagnosisId(diagnoses.get(26-1).getDiagnosisId()).wardId(wards.get(13-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Марат").lastName("Максимов").fatherName("Максимович").diagnosisId(diagnoses.get(27-1).getDiagnosisId()).wardId(wards.get(13-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Марк").lastName("Маратов").fatherName("Маратович").diagnosisId(diagnoses.get(27-1).getDiagnosisId()).wardId(wards.get(14-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Мурат").lastName("Марков").fatherName("Маркович").diagnosisId(diagnoses.get(28-1).getDiagnosisId()).wardId(wards.get(14-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Михаил").lastName("Муратов").fatherName("Муратович").diagnosisId(diagnoses.get(28-1).getDiagnosisId()).wardId(wards.get(14-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Наиль").lastName("Михаилов").fatherName("Михайлович").diagnosisId(diagnoses.get(30-1).getDiagnosisId()).wardId(wards.get(15-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Николай").lastName("Наильев").fatherName("Наильевич").diagnosisId(diagnoses.get(30-1).getDiagnosisId()).wardId(wards.get(15-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Олег").lastName("Николаев").fatherName("Николаевич").diagnosisId(diagnoses.get(30-1).getDiagnosisId()).wardId(wards.get(15-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Оливер").lastName("Олегов").fatherName("Олегович").diagnosisId(diagnoses.get(31-1).getDiagnosisId()).wardId(wards.get(16-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Оскар").lastName("Оливеров").fatherName("Оливерович").diagnosisId(diagnoses.get(31-1).getDiagnosisId()).wardId(wards.get(16-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Петр").lastName("Оскаров").fatherName("Оскарович").diagnosisId(diagnoses.get(32-1).getDiagnosisId()).wardId(wards.get(16-1).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Потап").lastName("Петров").fatherName("Петрович").diagnosisId(diagnoses.get(32-1).getDiagnosisId()).wardId(wards.get(15).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Прохар").lastName("Потапов").fatherName("Потапович").diagnosisId(diagnoses.get(33-1).getDiagnosisId()).wardId(wards.get(15).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Равиль").lastName("Прохаров").fatherName("Прохарович").diagnosisId(diagnoses.get(33-1).getDiagnosisId()).wardId(wards.get(16).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Рамазан").lastName("Равильев").fatherName("Равильевич").diagnosisId(diagnoses.get(34-1).getDiagnosisId()).wardId(wards.get(16).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Ратмир").lastName("Рамазанов").fatherName("Рамазанович").diagnosisId(diagnoses.get(34-1).getDiagnosisId()).wardId(wards.get(16).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Роман").lastName("Ратмиров").fatherName("Ратмирович").diagnosisId(diagnoses.get(36-1).getDiagnosisId()).wardId(wards.get(17).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Руслан").lastName("Романов").fatherName("Романович").diagnosisId(diagnoses.get(35-1).getDiagnosisId()).wardId(wards.get(17).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Рудольф").lastName("Русланов").fatherName("Русланович").diagnosisId(diagnoses.get(35-1).getDiagnosisId()).wardId(wards.get(17).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Рустам").lastName("Рустамов").fatherName("Рустамович").diagnosisId(diagnoses.get(37-1).getDiagnosisId()).wardId(wards.get(18).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Рафаэль").lastName("Рустамов").fatherName("Рустамович").diagnosisId(diagnoses.get(37-1).getDiagnosisId()).wardId(wards.get(18).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Савва").lastName("Рафаэльев").fatherName("Рафаэльевич").diagnosisId(diagnoses.get(38-1).getDiagnosisId()).wardId(wards.get(18).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Самат").lastName("Саввов").fatherName("Саввович").diagnosisId(diagnoses.get(39-1).getDiagnosisId()).wardId(wards.get(18).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Сергей").lastName("Саматов").fatherName("Саматович").diagnosisId(diagnoses.get(38-1).getDiagnosisId()).wardId(wards.get(18).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Тимур").lastName("Сергеев").fatherName("Сергеевич").diagnosisId(diagnoses.get(40-1).getDiagnosisId()).wardId(wards.get(19).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Тарас").lastName("Тимуров").fatherName("Тимурович").diagnosisId(diagnoses.get(41-1).getDiagnosisId()).wardId(wards.get(19).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Тамерлан").lastName("Тарасов").fatherName("Тарасович").diagnosisId(diagnoses.get(42-1).getDiagnosisId()).wardId(wards.get(19).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Томас").lastName("Тамерланов").fatherName("Тамерланович").diagnosisId(diagnoses.get(40-1).getDiagnosisId()).wardId(wards.get(20).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Федор").lastName("Томасов").fatherName("Томасович").diagnosisId(diagnoses.get(40-1).getDiagnosisId()).wardId(wards.get(20).getWardId()).build());
        peopleDao.insert(People.builder().firstName("Эрик").lastName("Федоров").fatherName("Федорович").diagnosisId(diagnoses.get(37-1).getDiagnosisId()).wardId(wards.get(20).getWardId()).build());
    }
}
