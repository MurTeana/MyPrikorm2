using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using MyPrikormWebAPI.Repositories;
using MyPrikormWebAPI.Model.DB.Entities;
using NLog;

namespace MyPrikormWebAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class MealsController : ControllerBase
    {
        private MealRepository mealRepository;
        private static Logger logger = LogManager.GetCurrentClassLogger();
        public MealsController(MealRepository mealRepository)
        {
            this.mealRepository = mealRepository;
        }

        [HttpGet]
        public async Task<ActionResult<List<Meal>>> Get()
        {
            try
            {
                return Ok(await mealRepository.GetAll());
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                return BadRequest(ex);
            }
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<Meal>> Get(int id)
        {
            try
            {
                Meal meal = await mealRepository.Get(id);
                if (meal == null)
                {
                    logger.Info("Meal is not found: " + id + ".");
                    return NotFound();
                }

                return Ok(meal);
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                return BadRequest(ex);
            }
        }

        [HttpPost]
        public ActionResult<Meal> Post(Meal meal)
        {
            if (!ModelState.IsValid)
            {
                logger.Info("Meal ModelState is not valid.");
                return BadRequest();
            }

            try
            {
                return Ok(mealRepository.Create(meal));
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                return BadRequest(ex);
            }
        }

        [HttpPut("{id}")]
        public ActionResult<Meal> Put(long id, Meal meal)
        {
            if (!ModelState.IsValid)
            {
                logger.Info("Meal ModelState is not valid.");
                return BadRequest();
            }

            try
            {
                return Ok(mealRepository.Update(meal));
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                return BadRequest(ex);
            }
        }

        [HttpDelete("{id}")]
        public ActionResult<Meal> Delete(int id)
        {
            try
            {
                Meal meal = mealRepository.Delete(id);
                if (meal == null)
                {
                    logger.Info("Meal is not found: " + id + ".");
                    return NotFound();
                }

                return NoContent();
            }
            catch (Exception ex)
            {
                logger.Error(ex);
                return BadRequest(ex);
            }
        }
    }
}
