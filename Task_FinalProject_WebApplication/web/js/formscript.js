
function OnSubmitForm()
{
    if(document.pressed == 'findByBreedID')
    {
        document.searchForm.action ="/pets/findpetbybreed.html?page=1";
    }
    else
    if(document.pressed == 'findByShelterID')
    {
        document.searchForm.action ="/pets/findpetbyshelter.html?page=1";
    }
    else
    if(document.pressed == 'findByBirthDate')
    {
        document.searchForm.action ="/pets/findpetbybirthdate.html?page=1";
    }
    return true;
}

function toggle_adoptionEnd()
{
    var cb1 = document.getElementById("adoptForTimeID").checked;
    document.getElementById("adoptionEndID").disabled = !cb1;
}