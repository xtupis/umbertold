const doctors = [
    {
        name: "Иванов Иван Иванович",
        specialization: "Терапевт",
        city: "Москва",
        experience: 10
    },
    {
        name: "Петрова Анна Сергеевна",
        specialization: "Кардиолог",
        city: "Санкт-Петербург",
        experience: 7
    },
    {
        name: "Смирнов Алексей Викторович",
        specialization: "Хирург",
        city: "Казань",
        experience: 12
    }
];

function searchDoctors() {
    const query = document.getElementById("searchInput").value.toLowerCase();
    const list = document.getElementById("doctorsList");

    list.innerHTML = "";

    const filtered = doctors.filter(d =>
        d.specialization.toLowerCase().includes(query) ||
        d.city.toLowerCase().includes(query)
    );

    if (filtered.length === 0) {
        list.innerHTML = "<p>Ничего не найдено</p>";
        return;
    }

    filtered.forEach(d => {
        const card = document.createElement("div");
        card.className = "card";
        card.innerHTML = `
            <h3>${d.name}</h3>
            <span><b>Специальность:</b> ${d.specialization}</span>
            <span><b>Город:</b> ${d.city}</span>
            <span><b>Стаж:</b> ${d.experience} лет</span>
        `;
        list.appendChild(card);
    });
}

// Показать всех при загрузке
searchDoctors();
