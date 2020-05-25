<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>${title}</title>
</head>
<body>
  <h1>${title}</h1>

  <p>${exampleObject.name} by ${exampleObject.developer}</p>

  <ul>
    <#list systems as system>
      <li>${system_index + 1}. ${system.name} from ${system.location}</li>
    </#list>
  </ul>
</body>
</html>