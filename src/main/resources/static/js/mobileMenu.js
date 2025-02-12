export function initializeMobileMenu() {
	const mobileMenuButton = document.getElementById('mobile-menu-button');
	const mobileMenu = document.getElementById('mobile-menu');
	const mobileMenuClose = document.getElementById('close-menu');

	mobileMenuButton.addEventListener('click', function() {
		mobileMenu.classList.toggle('hidden');
	});

	document.addEventListener('click', function(e) {
		if (!mobileMenu.contains(e.target) && !mobileMenuButton.contains(e.target)) {
			mobileMenu.classList.add('hidden');
		}
	});

	mobileMenuClose.addEventListener('click', function() {
		document.getElementById('mobile-menu').classList.add('hidden');
	});
}