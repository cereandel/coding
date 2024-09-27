const input = document.querySelector("input");
const button = document.querySelector(".searchButton");

const user = document.querySelector(".githubName");
const login = document.querySelector(".githubUsername");
const joined = document.querySelector(".joinedDate");
const repo = document.querySelector(".repoTotal");
const follower = document.querySelector(".followersTotal");
const followings = document.querySelector(".followingTotal");
const locations = document.querySelector(".locations");
const xs = document.querySelector(".xs");
const websites = document.querySelector(".websites");
const companies = document.querySelector(".companies");
const gitBio = document.querySelector(".githubBio");

let img = document.createElement("img");
let block = document.querySelector(".mainImage");

async function fetchGithub() {
    const url = `https://api.github.com/users/${input.value}`;
    const response = await fetch(url);
    const data = await response.json();
    const dateData = data.created_at.slice(0, data.created_at.length - 10);

    img.src = data.avatar_url;
    block.appendChild(img);
    block.style.border = "none";

    user.innerHTML = data.name;
    login.innerHTML = `@${data.login}`;
    joined.innerHTML = `Joined ${dateData}`;
    repo.innerHTML = data.public_repos;
    follower.innerHTML = data.followers;
    followings.innerHTML = data.following;
    locations.innerHTML =
        data.location === "" || data.location === null
            ? "No Location"
            : data.location;
    xs.innerHTML =
        data.twitter_username === "" || data.twitter_username === null
            ? "No X"
            : data.twitter_username;
    websites.innerHTML =
        data.blog === "" || data.blog === null ? "No Website" : data.blog;
    companies.innerHTML =
        data.company === "" || data.company === null
            ? "No Company"
            : data.company;
    gitBio.innerHTML =
        data.bio === "" || data.bio === null
            ? "This profile has no bio..."
            : data.bio;
}

button.addEventListener("click", function () {

    fetchGithub();
    input.value = "";
});

input.addEventListener("keydown", function (event) {
    if (event.key === "Enter") {
        fetchGithub();
    }
});