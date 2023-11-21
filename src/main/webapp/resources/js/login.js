document.querySelectorAll('.form-input').forEach((inputWrapper) => {
    const input = inputWrapper.querySelector('input');
    const label = inputWrapper.querySelector('label');

    input.addEventListener('focus', () => {
        label.classList.add('label-shrink');
    });

    input.addEventListener('blur', () => {
        if (input.value === '') {
            label.classList.remove('label-shrink');
        }
    });
});