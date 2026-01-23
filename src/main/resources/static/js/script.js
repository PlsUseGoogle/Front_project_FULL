/**
 * Новый script.js для работы со Spring Boot
 * Вся работа с данными (проекты, студенты) перенесена в Java-контроллеры.
 */

// Выход из системы
// Теперь мы просто перенаправляем на главную, так как сессией управляет сервер
function handleLogout() {
    // Если ты используешь Spring Security, лучше делать редирект на /logout
    window.location.href = '/index';
}

// Инициализация визуальных эффектов
document.addEventListener('DOMContentLoaded', () => {

    // Обработка кнопки выхода
    document.querySelectorAll('#logout').forEach(button => {
        button.addEventListener('click', (e) => {
            e.preventDefault();
            if (confirm('Czy na pewno chcesz się wylogować?')) {
                handleLogout();
            }
        });
    });

    // Пример: подтверждение удаления проекта (если добавишь кнопку удаления в HTML)
    const deleteButtons = document.querySelectorAll('.btn-delete');
    deleteButtons.forEach(btn => {
        btn.addEventListener('click', (e) => {
            if (!confirm('Czy na pewno chcesz usunąć ten element?')) {
                e.preventDefault();
            }
        });
    });
});