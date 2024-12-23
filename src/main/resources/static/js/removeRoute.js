function removeRoute(button) {
	const routeId = button.getAttribute("data-id");
	const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
	const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

	fetch('/remove-route', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded',
			[csrfHeader]: csrfToken
		},
		body: new URLSearchParams({ id: routeId })
	})
		.then(response => {
			if (response.ok) {
				const routeItem = button.closest('li');
				if (routeItem) {
					routeItem.remove();
				}
			} else {
				alert('Failed to delete the route. Please try again.');
			}
		})
		.catch(error => {
			console.error('Error deleting route:', error);
			alert('An error occurred while deleting the route.');
		});
}