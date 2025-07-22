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
    console.error("Required DOM elements not found");
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
      throw new Error(`HTTP error! status: ${response.status}`);
    }

    const data: DogApiResponse = await response.json();

    if (data.status !== "success") {
      throw new Error("API returned an error");
    }

    loading.style.display = "none";
    dogImg.src = data.message;
    dogImg.style.display = "block";
  } catch (error: unknown) {
    loading.style.display = "none";
    errorDiv.style.display = "block";

    if (error instanceof Error) {
      console.error("Error fetching dog image:", error.message);
    } else {
      console.error("Unknown error occurred:", error);
    }
  }
}

(window as any).getDogImage = getDogImage;
