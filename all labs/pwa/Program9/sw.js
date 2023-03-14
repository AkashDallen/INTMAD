self.addEventListener('install', () => {
    console.log("Installed")
})

self.addEventListener('activate', () => {
    console.log("Activated")
})

self.addEventListener('fetch', ()=> {
    fetch("https://api.github.com/users/photonwings")
        .then( e => {
            return e.json()
        })
        .then(e => {
            console.log(e)
        })
        .catch(e => {
            console.log(e)
        })
})