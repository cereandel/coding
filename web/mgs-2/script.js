window.addEventListener('scroll', function() {
    const headerImage = document.querySelector('.header-image');
    const scrollPosition = window.scrollY;
    const headerHeight = document.querySelector('header').offsetHeight;
    
    const opacity = 1 - (scrollPosition / (headerHeight * 6));
    
    headerImage.style.opacity = Math.max(0, Math.min(1, opacity));

    const gameplaySection = document.querySelector('.Gameplay');
    if (gameplaySection) {
        const sectionTop = gameplaySection.getBoundingClientRect().top;
        const sectionHeight = gameplaySection.offsetHeight;
        const windowHeight = window.innerHeight;

        if (sectionTop < windowHeight && sectionTop + sectionHeight > 0) {
            const gameplayOpacity = (windowHeight - sectionTop) / (windowHeight * 1.01);
            gameplaySection.style.opacity = Math.max(0, Math.min(1, gameplayOpacity));
        } else {
            gameplaySection.style.opacity = 0;
        }
    }
});