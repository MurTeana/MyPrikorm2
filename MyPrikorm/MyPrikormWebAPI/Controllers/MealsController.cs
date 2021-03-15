using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using MyPrikormWebAPI.Repositories;
using MyPrikormWebAPI.DB.Entities;

namespace MyPrikormWebAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class MealsController : ControllerBase
    {
        private MealRepository rep;

        public MealsController(MealRepository rep)
        {
            this.rep = rep;
        }

        // GET api/meal
        //[EnableCors("AnotherPolicy")]
        [HttpGet]
        public async Task<ActionResult<List<Meal>>> Get()
        {
            return await rep.GetAll();
        }

        // GET api/values/5
        //[EnableCors("AnotherPolicy")]
        [HttpGet("{id}")]
        public async Task<ActionResult<Meal>> Get(int id)
        {
            Meal meal = await rep.Get(id);
            if (meal == null)
            {
                return NotFound();
            }
            return new ObjectResult(meal);
        }

        // POST api/values
        //[EnableCors("AnotherPolicy")]
        [HttpPost]
        public async Task<ActionResult<Meal>> Post(Meal meal)
        {
            if (meal == null)
            {
                return BadRequest();
            }

            return Ok(await rep.Create(meal));
        }

        // PUT api/values/5
        //[EnableCors("AnotherPolicy")]
        [HttpPut("{id}")]
        public async Task<ActionResult<Meal>> Put(long id, Meal meal)
        {
            if (meal == null)
            {
                return BadRequest();
            }

            return Ok(await rep.Update(meal));
        }

        // DELETE api/values/5
        //[EnableCors("AnotherPolicy")]
        [HttpDelete("{id}")]
        public async Task<ActionResult<Meal>> Delete(int id)
        {
            Meal meal = await rep.Delete(id);
            if (meal == null)
            {
                return NotFound();
            }
            return new ObjectResult(meal);
        }
    }
}
