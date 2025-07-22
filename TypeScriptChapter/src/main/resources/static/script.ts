interface DogApiResponse {
    message: string;
    status: string;
}

async function getDogImage(): Promise<void> {
    const dogImg: HTMLImageElement | null = document.getElementById(
        "dogImage"
    ) as HTMLImageElement;
    const loading: HTMLElement | null = document.getElementById("loading");
    const errorDiv: HTMLElement | null = document.getElementById("error");

    if (!dogImg || !loading || !errorDiv) {
        return;
    }

    loading.style.display = "block";
    dogImg.style.display = "none";
    errorDiv.style.display = "none";

    try {
        const response: Response = await fetch(
            "https://dog.ceo/api/breeds/image/random"
        );

        if (!response.ok) {
            throw new Error(`Http error! status: ${response.status}`);
        }

        const data: DogApiResponse = await response.json();

        if (data.status !== "success") {
            throw new Error("API returned an error");
        }

        loading.style.display = "none";
        dogImg.src = data.message;
        dogImg.style.display = "block";
    } catch (error) {
        loading.style.display = "none";
        errorDiv.style.display = "block";


    }
}


