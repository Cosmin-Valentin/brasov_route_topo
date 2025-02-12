export function initializeDarkMode() {
	const darkModeToggle = document.getElementById("dark-mode-toggle");
	const body = document.body;
	const savedDarkMode = localStorage.getItem('darkMode');

	if(savedDarkMode == 'true') {
		body.classList.add('dark')
		darkModeToggle.textContent = 'Light Mode';
	} else {
		darkModeToggle.textContent = 'Dark Mode';
	}

	darkModeToggle.addEventListener('click', () => {
		body.classList.toggle('dark');
		
		if(body.classList.contains('dark')) {
			localStorage.setItem('darkMode', 'true');
			darkModeToggle.textContent = 'Light Mode';
		} else {
			localStorage.setItem('darkMode', 'false');
			darkModeToggle.textContent = 'Dark mode';
		}
	});
}