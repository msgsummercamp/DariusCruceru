async function getDogImage(){
  
    const dogImg = document.getElementById("dogImage");
    const loading = document.getElementById("loading");
    const errorDiv = document.getElementById("error");
    
  
    loading.style.display = "block";
    dogImg.style.display = "none";
    errorDiv.style.display = "none";
    
    try {
        const response = await fetch("https://dog.ceo/api/breeds/image/random");
        
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        
        const data = await response.json();
        
        
        if (data.status !== "success") {
            throw new Error("API returned error status");
        }
        
       
        loading.style.display = "none";
        dogImg.src = data.message;
        dogImg.style.display = "block";
        
        console.log(data.message);
    } catch (error) {
        // Hide loading and show error
        loading.style.display = "none";
        errorDiv.style.display = "block";
        console.error("Error fetching dog image:", error);
    }
}